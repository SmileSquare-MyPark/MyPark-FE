//
//  AppDelegate.swift
//  iosApp
//
//  Created by 김기찬 on 9/27/25.
//
import UIKit
import KakaoSDKCommon
import KakaoSDKAuth
import KakaoSDKUser

class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        if let kakaoAppKey = Bundle.main.object(forInfoDictionaryKey: "KakaoAppKey") as? String {
            KakaoSDK.initSDK(appKey: kakaoAppKey)
            KakaoIosStarter.setupObservers()
        }

        return true
    }

    func application(_ app: UIApplication, open url: URL,
                     options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        if (AuthApi.isKakaoTalkLoginUrl(url)) {
            return AuthController.handleOpenUrl(url: url)
        }
        return false
    }
}
