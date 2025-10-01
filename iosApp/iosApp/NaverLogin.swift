//
// Created by 김기찬 on 2025. 9. 30..
//

import Foundation
import NidThirdPartyLogin
import ComposeApp

private let NAVER_LOGIN_START = Notification.Name("NAVER_LOGIN_START")

enum NaverIosStarter {
    static func setupObservers() {
        NotificationCenter.default.addObserver(
            forName: NAVER_LOGIN_START,
            object: nil,
            queue: .main
        ) { _ in
            start()
        }
    }

    static func start() {
        NidOAuth.shared.requestLogin { result in
            switch result {
            case .success(let loginResult):
                print(loginResult.accessToken.tokenString)
                AuthNaverResultBridge.shared.naverLoginSuccess(token: loginResult.accessToken.tokenString)
            case .failure(let error):
                AuthNaverResultBridge.shared.naverLoginFailure(message: error.localizedDescription)
            }
        }
    }
}
