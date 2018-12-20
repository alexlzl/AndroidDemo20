package com.example.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.accountmanager.ConstantsGlobal.KEY_ACCOUNT;


/**
 * date：2017/9/18 on 17:30
 * 账户登陆成功页面
 */
public class MainActivity extends AppCompatActivity {

    private Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOut = (Button) findViewById(R.id.btn_out);
        init();
    }

    /**
     * 登陆进来数据的判断
     */
    private void init() {
        final Account account = getIntent().getParcelableExtra(KEY_ACCOUNT);
        final AccountManager accountManager = AccountManager.get(this);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManagerCallback<Bundle> callback = new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            String token = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                            accountManager.invalidateAuthToken(ConstantsGlobal.ACCOUNT_TYPE, token);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                accountManager.getAuthToken(account, ConstantsGlobal.AUTH_TOKEN_TYPE, null, MainActivity.this, callback, null);
            }
        });
    }
}
