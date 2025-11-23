package com.smile.mypark.data.local

import kotlinx.cinterop.memScoped
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFBooleanTrue
import platform.Security.*
import platform.Foundation.*


class LocalStorageIos : LocalStorage {

    private val service = "mypark_service"

    override suspend fun setAccessToken(token: String) {
        save("access", token)
    }

    override suspend fun getAccessToken(): String? {
        return load("access")
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        save("refresh", refreshToken)
    }

    override suspend fun getRefreshToken(): String? {
        return load("refresh")
    }

    override suspend fun setFirstRun(firstRun: Boolean) {
        save("first_run", firstRun.toString())
    }

    override suspend fun getFirstRun(): Boolean {
        return load("first_run")?.toBoolean() ?: true
    }

    override suspend fun clear() {
        delete("access")
        delete("refresh")
        delete("first_run")
    }


    // --------------------------
    // Keychain helper functions
    // --------------------------

    private fun save(key: String, value: String) {
        val data = value.encodeToByteArray().toNSData()

        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key
        )

        // 기존 값 제거
        SecItemDelete(query as CFDictionaryRef)

        // 새 데이터 추가
        val insert = query + (kSecValueData to data)
        SecItemAdd(insert as CFDictionaryRef, null)
    }

    private fun load(key: String): String? = memScoped {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key,
            kSecReturnData to kCFBooleanTrue!!,
            kSecMatchLimit to kSecMatchLimitOne
        )

        val resultPtr = alloc<CFTypeRefVar>()
        val status = SecItemCopyMatching(query as CFDictionaryRef, resultPtr.ptr)
        if (status != errSecSuccess) return null

        val data = resultPtr.value as NSData
        return data.toByteArray().decodeToString()
    }

    private fun delete(key: String) {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key
        )
        SecItemDelete(query as CFDictionaryRef)
    }
}
