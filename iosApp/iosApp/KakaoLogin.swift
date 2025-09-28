//
//  KakaoLogin.swift
//  iosApp
//
//  Created by 김기찬 on 9/28/25.
//

import Foundation
import KakaoSDKUser
import KakaoSDKAuth
import ComposeApp

private let KAKAO_LOGIN_START = Notification.Name("KAKAO_LOGIN_START")

enum KakaoIosStarter {
    static func setupObservers() {
        NotificationCenter.default.addObserver(
            forName: KAKAO_LOGIN_START,
            object: nil,
            queue: .main
        ) { _ in
            start()
        }
    }

    static func start() {
        if (UserApi.isKakaoTalkLoginAvailable()) {
            UserApi.shared.loginWithKakaoTalk { oauthToken, error in
                handle(oauthToken: oauthToken, error: error)
            }
        } else {
            UserApi.shared.loginWithKakaoAccount { oauthToken, error in
                handle(oauthToken: oauthToken, error: error)
            }
        }
    }

    private static func handle(oauthToken: OAuthToken?, error: Error?) {
        if let error = error {
            AuthKakaoResultBridge.shared.kakaoLoginFailure(message: error.localizedDescription)
        } else if let token = oauthToken?.accessToken {
            AuthKakaoResultBridge.shared.kakaoLoginSuccess(token: token)
        } else {
            AuthKakaoResultBridge.shared.kakaoLoginFailure(message: "No token")
        }
    }
}