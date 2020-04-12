package com.xteamsoft.digitalpumper.FingerprintManagerLT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.os.CancellationSignal;
import android.util.Log;

import com.xteamsoft.digitalpumper.R;

@SuppressLint({"NewApi"})
public class FingerprintHelper extends FingerprintManager.AuthenticationCallback {
    private Context context;

    private final Callback mCallback;

    private CancellationSignal mCancellationSignal;

    private final FingerprintManager mFingerprintManager;

    private boolean mSelfCancelled;

    public FingerprintHelper(Context paramContext, FingerprintManager paramFingerprintManager, Callback paramCallback) {
        this.context = paramContext;
        this.mFingerprintManager = paramFingerprintManager;
        this.mCallback = paramCallback;
    }

    private void showError(CharSequence paramCharSequence) {
        this.mCallback.onError(paramCharSequence.toString());
    }

    public boolean isFingerprintAuthAvailable() {
        return (this.mFingerprintManager.isHardwareDetected() && this.mFingerprintManager.hasEnrolledFingerprints());
    }

    public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {
        Log.e("ljl","onAuthenticationError");
        if (!this.mSelfCancelled) {
            showError(paramCharSequence);
            this.mCallback.onError(paramCharSequence.toString());
        }
    }

    public void onAuthenticationFailed() {
        Log.e("ljl","onAuthenticationFailed");
                showError(this.context.getResources().getString(R.string.exit_current_num));
    }

    public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {
        showError(paramCharSequence);
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAuthenticationResult) {
        Log.e("ljl","onAuthenticationFailed");
        this.mCallback.onAuthenticated("成功了");
    }

    public void startListening(FingerprintManager.CryptoObject paramCryptoObject) {
        if (!isFingerprintAuthAvailable())
            return;
        this.mCancellationSignal = new CancellationSignal();
        this.mSelfCancelled = false;
       // this.mFingerprintManager.authenticate(paramCryptoObject, this.mCancellationSignal, 0, this, null);
    }

    public void stopListening() {
        if (this.mCancellationSignal != null) {
            this.mSelfCancelled = true;
            this.mCancellationSignal.cancel();
            this.mCancellationSignal = null;
        }
    }

    public static interface Callback {
        void onAuthenticated(String param1String);

        void onError(String param1String);
    }
}
