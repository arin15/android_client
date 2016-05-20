package me.kaede.mvp.login.presenter;

import android.os.Handler;
import android.os.Looper;

import me.kaede.mvp.login.model.IUserConsumer;
import me.kaede.mvp.login.model.UserConsumer;
import me.kaede.mvp.login.view.ILoginView;

/**
 * Created by kaede on 2015/5/18.
 */
public class LoginPresenterCompl implements ILoginPresenter {
	ILoginView iLoginView;
	IUserConsumer user;
	Handler    handler;

	public LoginPresenterCompl(ILoginView iLoginView) {
		this.iLoginView = iLoginView;
		user = new UserConsumer();
		handler = new Handler(Looper.getMainLooper());
	}

	@Override
	public void clear() {
		iLoginView.onClearText();
	}

	@Override
	public void doLogin(String name, String passwd) {
		Boolean isLoginSuccess = true;
		//final int code = user.checkUserValidity(name,passwd);	//모델
		final int code = 0;
		user.getUser(name, passwd);
		if (code!=0) isLoginSuccess = false;
		final Boolean result = isLoginSuccess;
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
					iLoginView.onLoginResult(result, code);
			}
		}, 5000);

	}

	@Override
	public void setProgressBarVisiblity(int visiblity){
		iLoginView.onSetProgressBarVisibility(visiblity);
	}
}
