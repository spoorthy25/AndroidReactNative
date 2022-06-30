package com.example.rnintegration

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage


class ReactRenderActivity : Activity(), DefaultHardwareBackBtnHandler {
        private var mReactRootView: ReactRootView? = null
        private var mReactInstanceManager: ReactInstanceManager? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mReactRootView = ReactRootView(this)
            mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(application)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index.android")
                .addPackage(MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .setCurrentActivity(this)
                .build()

            val initialProperties = Bundle()
            initialProperties.putString("message_from_native", intent?.extras?.get("message_from_native")?.toString())
            mReactRootView!!.startReactApplication(mReactInstanceManager, "RNIntegration", initialProperties)
            setContentView(mReactRootView)
        }

        protected fun getMainComponentName(): String? {
            return "RNIntegration"
        }

        protected fun createReactActivityDelegate(): ReactActivityDelegate? {
                return object : ReactActivityDelegate(this, getMainComponentName()) {
                    override fun getLaunchOptions(): Bundle? {
                        val initialProperties = Bundle()
                        initialProperties.putString("var_1", "Im the first var")
                        return initialProperties
                    }
                }
        }

        override fun invokeDefaultOnBackPressed() {
            super.onBackPressed()
        }

        override fun onPause() {
            super.onPause()
            if (mReactInstanceManager != null) {
                mReactInstanceManager!!.onHostPause(this)
            }
        }

        override fun onResume() {
            super.onResume()
            if (mReactInstanceManager != null) {
                mReactInstanceManager!!.onHostResume(this, this)
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            if (mReactInstanceManager != null) {
                mReactInstanceManager!!.onHostDestroy(this)
            }
        }

        override fun onBackPressed() {
            if (mReactInstanceManager != null) {
                mReactInstanceManager!!.onBackPressed()
            } else {
                super.onBackPressed()
            }
        }

        override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
                mReactInstanceManager!!.showDevOptionsDialog()
                return true
            }
            return super.onKeyUp(keyCode, event)
        }
    }