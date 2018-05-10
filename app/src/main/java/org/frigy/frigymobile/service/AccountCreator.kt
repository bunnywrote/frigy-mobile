package org.frigy.frigymobile.service

import android.content.Context.ACCOUNT_SERVICE
import android.accounts.AccountManager
import android.accounts.Account
import android.content.Context

class AccountCreator {
    companion object {
        val AUTHORITY = "AndroidManifest.xml"
        // An account type, in the form of a domain name
        val ACCOUNT_TYPE = "foodrepo.org"
        // The account name
        val ACCOUNT = "foodrepo.org"

        fun CreateSyncAccount(context: Context) {
            // Create the account type and default account
            val newAccount = Account(
                    ACCOUNT, ACCOUNT_TYPE)
            // Get an instance of the Android account manager
            val accountManager = context.getSystemService(
                    ACCOUNT_SERVICE) as AccountManager
            /*
             * Add the account and account type, no password or user data
             * If successful, return the Account object, otherwise report an error.
             */
            if (accountManager.addAccountExplicitly(newAccount, null, null)) {
                /*
                 * If you don't set android:syncable="true" in
                 * in your <provider> element in the manifest,
                 * then call context.setIsSyncable(account, AUTHORITY, 1)
                 * here.
                 */
            } else {
                /*
                 * The account exists or some other error occurred. Log this, report it,
                 * or handle it internally.
                 */
            }
        }
    }

}