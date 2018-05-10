package org.frigy.frigymobile.service

import android.accounts.AbstractAccountAuthenticator
import android.content.Context
import android.accounts.AccountAuthenticatorResponse
import android.os.Bundle
import android.accounts.NetworkErrorException
import android.accounts.Account

class ProductDataAuthenticator(context: Context?) : AbstractAccountAuthenticator(context) {

    override fun editProperties(
            r: AccountAuthenticatorResponse, s: String): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(
            r: AccountAuthenticatorResponse,
            s: String,
            s2: String,
            strings: Array<String>,
            bundle: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(
            r: AccountAuthenticatorResponse,
            account: Account,
            bundle: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(
            r: AccountAuthenticatorResponse,
            account: Account,
            s: String,
            bundle: Bundle): Bundle {
        throw UnsupportedOperationException()
    }

    override fun getAuthTokenLabel(s: String): String {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(
            r: AccountAuthenticatorResponse,
            account: Account,
            s: String, bundle: Bundle): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(
            r: AccountAuthenticatorResponse,
            account: Account, strings: Array<String>): Bundle {
        throw UnsupportedOperationException()
    }
}