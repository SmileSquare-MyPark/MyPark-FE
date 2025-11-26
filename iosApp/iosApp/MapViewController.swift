//
// Created by 김기찬 on 2025. 11. 3..
//

import UIKit
import NMapsMap
import CoreLocation
import ComposeApp

final class HitTestPassThroughView: UIView {
    override func point(inside point: CGPoint, with event: UIEvent?) -> Bool {
        for sub in subviews where sub.isUserInteractionEnabled && !sub.isHidden && sub.alpha > 0.01 {
            let p = sub.convert(point, from: self)
            if sub.hitTest(p, with: event) != nil {
                return true
            }
        }
        return false
    }
}

final class MapViewController: UIViewController, CLLocationManagerDelegate {
    private let mapView = NMFNaverMapView()
    private let topOverlay = HitTestPassThroughView()
    private let buttonsOverlay = HitTestPassThroughView()
    private let bottomOverlay = HitTestPassThroughView()

    private var didInitCamera = false
    private let locationManager = CLLocationManager()

    private var stores: [ComposeApp.StoreUi] = []
    private var selectedStore: ComposeApp.StoreUi? = nil

    private var bottomVC: UIViewController?
    private var buttonsVC: UIViewController?

    private func makeTransparent(_ view: UIView) {
        view.isOpaque = false
        view.backgroundColor = .clear
        view.subviews.forEach { makeTransparent($0) }
    }

    private func attach(child vc: UIViewController, to container: UIView) {
        addChild(vc)
        let v = vc.view!
        v.translatesAutoresizingMaskIntoConstraints = false
        makeTransparent(v)
        container.addSubview(v)
        NSLayoutConstraint.activate([
                                        v.leadingAnchor.constraint(equalTo: container.leadingAnchor),
                                        v.trailingAnchor.constraint(equalTo: container.trailingAnchor),
                                        v.topAnchor.constraint(equalTo: container.topAnchor),
                                        v.bottomAnchor.constraint(equalTo: container.bottomAnchor),
                                    ])
        vc.didMove(toParent: self)
    }

    private func clear(child vc: UIViewController?) {
        guard let vc = vc else { return }
        vc.willMove(toParent: nil)
        vc.view.removeFromSuperview()
        vc.removeFromParent()
    }

    private func updateBottomAndButtons() {
        clear(child: bottomVC)
        clear(child: buttonsVC)

        guard let selected = selectedStore else {
            return
        }

        let isLiked = selected.isLiked

        // 하단 카드
        let bottom = ComposeApp.MapComponentKt.MapOverlayBottomCardViewController(
            selected: selected,
            onOpenList: {
                // TODO: 목록 화면으로 이동
            },
            onCall: { store in
                // TODO: 전화 걸기 (tel://store.phone)
                print("[iOS] call: \(store.phone)")
            },
            onRoute: { store in
                // TODO: 네이버 지도 길찾기 등
                print("[iOS] route to: \(store.name)")
            }
        )
        attach(child: bottom, to: bottomOverlay)
        bottomVC = bottom

        // 하트/목록/내위치 버튼
        let buttons = ComposeApp.MapComponentKt.MapOverlayFloatButtonsViewController(
            onAddList: { [weak self] in
                guard let self = self, let current = self.selectedStore else { return }

                let currentLiked = current.isLiked
                let newLiked = !currentLiked

                let updated = ComposeApp.StoreUi(
                    shopCode: current.shopCode,
                    name: current.name,
                    slots: current.slots,
                    distanceText: current.distanceText,
                    address: current.address,
                    rating: current.rating,
                    phone: current.phone,
                    lat: current.lat,
                    lng: current.lng,
                    isLiked: newLiked
                )
                self.selectedStore = updated
                self.updateBottomAndButtons()

                ComposeApp.MapIosApi.shared.toggleLike(
                    shopCode: current.shopCode,
                    like: newLiked
                ) { error in
                    if let error = error {
                        print("[iOS] 찜 토글 실패: \(error.message ?? "unknown error")")
                        return
                    }
                    print("[iOS] 찜 토글 성공")
                }
            },
            onOpenList: {
                print("[iOS] 매장 목록 버튼 클릭")
                // TODO: 목록 팝업, 화면 이동 등
            },
            onLocation: { [weak self] in
                guard let self = self else { return }
                self.mapView.mapView.positionMode = .direction
                self.locationManager.startUpdatingLocation()
            },
            isLiked: isLiked
        )
        attach(child: buttons, to: buttonsOverlay)
        buttonsVC = buttons
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .clear
        view.isOpaque = false

        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestWhenInUseAuthorization()

        mapView.translatesAutoresizingMaskIntoConstraints = false
        mapView.backgroundColor = .clear
        mapView.isOpaque = false
        view.addSubview(mapView)
        NSLayoutConstraint.activate([
                                        mapView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                                        mapView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                                        mapView.topAnchor.constraint(equalTo: view.topAnchor),
                                        mapView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
                                    ])

        mapView.mapView.contentInset = .zero

        mapView.mapView.locationOverlay.hidden = false
        mapView.showLocationButton = true
        mapView.mapView.positionMode = .disabled

        [topOverlay, buttonsOverlay, bottomOverlay].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            makeTransparent($0)
            view.addSubview($0)
        }

        let topHeight: CGFloat = 200        // 타이틀 + 검색바 높이
        let bottomHeight: CGFloat = 240     // 하단 카드 높이
        let buttonsHeight: CGFloat = 44     // 가운데 플로팅 버튼 높이
        let buttonsBottomGap: CGFloat = bottomHeight + 30 // 카드 위 여백


        NSLayoutConstraint.activate([
                                        topOverlay.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                                        topOverlay.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                                        topOverlay.topAnchor.constraint(equalTo: view.topAnchor),
                                        topOverlay.heightAnchor.constraint(equalToConstant: topHeight),

                                        bottomOverlay.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                                        bottomOverlay.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                                        bottomOverlay.bottomAnchor.constraint(equalTo: view.bottomAnchor),
                                        bottomOverlay.heightAnchor.constraint(equalToConstant: bottomHeight),

                                        buttonsOverlay.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                                        buttonsOverlay.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                                        buttonsOverlay.heightAnchor.constraint(equalToConstant: buttonsHeight),
                                        buttonsOverlay.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -buttonsBottomGap),
                                    ])

        let topVC = ComposeApp.MapComponentKt.MapOverlayTopBarViewController(
            onBack: { [weak self] in self?.dismiss(animated: true) },
            onMenu: { /* TODO */ },
            onSearch: { [weak self] keyword in
                guard let self = self else { return }

                ComposeApp.MapIosApi.shared.searchStores(keyword: keyword) { result, error in
                    if let error = error {
                        print("[iOS] 매장 검색 실패: \(error.message ?? "unknown error")")
                        return
                    }
                    guard let result = result, !result.isEmpty else {
                        print("[iOS] 검색 결과 없음")
                        self.stores = []
                        self.selectedStore = nil
                        self.updateBottomAndButtons()
                        return
                    }

                    print("[iOS] 검색 결과 개수: \(result.count)")
                    self.stores = result
                    self.selectedStore = result.first
                    self.updateBottomAndButtons()

                    // TODO: 여기서 selectedStore의 좌표(lat/lng)가 있으면
                    // self.mapView.mapView.moveCamera(...) 로 카메라 이동도 가능
                }
            }
        )
        attach(child: topVC, to: topOverlay)

        updateBottomAndButtons()

        mapView.layer.zPosition = 0
        topOverlay.layer.zPosition = 1
        buttonsOverlay.layer.zPosition = 1
        bottomOverlay.layer.zPosition = 1

    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        guard !didInitCamera else { return }
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.016) { [weak self] in
            guard let self = self else { return }
            self.didInitCamera = true
            let coord = NMGLatLng(lat: 37.5665, lng: 126.9780)
            self.mapView.mapView.moveCamera(NMFCameraUpdate(scrollTo: coord))
            let status: CLAuthorizationStatus
            if #available(iOS 14.0, *) { status = self.locationManager.authorizationStatus }
            else { status = CLLocationManager.authorizationStatus() }
            self.applyPositionMode(for: status)
        }
    }

    @available(iOS 14.0, *)
    @objc func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        applyPositionMode(for: manager.authorizationStatus)
    }

    @objc func locationManager(_ manager: CLLocationManager,
                               didChangeAuthorization status: CLAuthorizationStatus) {
        applyPositionMode(for: status)
    }

    @objc func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let loc = locations.last else { return }
        let latLng = NMGLatLng(lat: loc.coordinate.latitude, lng: loc.coordinate.longitude)
        mapView.mapView.moveCamera(NMFCameraUpdate(scrollTo: latLng))
    }

    private func applyPositionMode(for status: CLAuthorizationStatus) {
        switch status {
        case .authorizedAlways, .authorizedWhenInUse:
            mapView.mapView.locationOverlay.hidden = false
            mapView.mapView.positionMode = .direction
            locationManager.startUpdatingLocation()
        default:
            mapView.mapView.positionMode = .disabled
            mapView.mapView.locationOverlay.hidden = true
            locationManager.stopUpdatingLocation()
        }
    }
}