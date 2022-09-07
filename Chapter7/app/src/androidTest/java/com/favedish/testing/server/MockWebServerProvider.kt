package com.favedish.testing.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import kotlin.concurrent.thread

class MockWebServerProvider {
    private val server by lazy {
        val result = MockWebServer()
        thread(priority = Thread.MAX_PRIORITY) {
            result.start()
        }.join()

        val keyStorePassword = "fake_password".toCharArray()
        val serverKeyStore = KeyStore.getInstance("BKS")
        processAssetStream("teststore_keystore.bks") { keyStoreStream ->
            serverKeyStore.load(keyStoreStream, keyStorePassword)
        }

        val algorithm = KeyManagerFactory.getDefaultAlgorithm()
        val keyManagerFactory = KeyManagerFactory.getInstance(algorithm)
        keyManagerFactory.init(serverKeyStore, keyStorePassword)

        val trustManagerFactory = TrustManagerFactory.getInstance(algorithm)
        trustManagerFactory.init(serverKeyStore)

        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(
            keyManagerFactory.keyManagers,
            trustManagerFactory.trustManagers,
            null
        )
        result.apply { useHttps(sslContext.socketFactory, false) }
    }
    val serverUrl: String
        get() {
            var result = ""
            thread(priority = Thread.MAX_PRIORITY) {
                result = server.hostName + ":" + server.port
            }.join()
            return result
        }

    fun mockWebServer(dispatcher: Dispatcher) = server.apply {
        this.dispatcher = dispatcher
    }
}
