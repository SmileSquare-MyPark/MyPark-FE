//
// Created by 김기찬 on 2025. 11. 3..
//

import UIKit

let OPEN_NATIVE_MAP = Notification.Name("OPEN_NATIVE_MAP")

enum MapIosStarter {
    private(set) static var isPresented = false

    static func setupObservers() {
        NotificationCenter.default.addObserver(
            forName: OPEN_NATIVE_MAP, object: nil, queue: .main
        ) { _ in presentMap() }
    }

    static func resetPresented() {
        isPresented = false
    }

    private static func presentMap() {
        guard !isPresented else { return }
        DispatchQueue.main.async {
            guard let top = topViewController() else { return }
            let nav = UINavigationController(rootViewController: MapViewController())
            nav.modalPresentationStyle = .fullScreen
            top.present(nav, animated: true)
            isPresented = true
        }
    }

    private static func topViewController(base: UIViewController? = {
        let scenes = UIApplication.shared.connectedScenes
        .compactMap { $0 as? UIWindowScene }
        .filter { $0.activationState == .foregroundActive }
        let keyWin = scenes.first?.windows.first(where: { $0.isKeyWindow })
        return keyWin?.rootViewController
    }()) -> UIViewController? {
        if let nav = base as? UINavigationController { return topViewController(base: nav.visibleViewController) }
        if let tab = base as? UITabBarController { return topViewController(base: tab.selectedViewController) }
        if let presented = base?.presentedViewController { return topViewController(base: presented) }
        return base
    }
}