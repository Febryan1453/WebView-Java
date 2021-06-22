package com.febryan.webview.ui.webview;

import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.febryan.webview.R;
import com.febryan.webview.databinding.WebViewFragmentBinding;

public class WebViewFragment extends Fragment {

    private WebViewFragmentBinding binding;

    private WebViewViewModel mViewModel;

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = WebViewFragmentBinding.bind(view);

        ProgressDialog loading = new ProgressDialog(getContext());
        loading.setMessage("Loading...");

        binding.webView.getSettings().setJavaScriptEnabled(true);

        binding.webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                loading.dismiss();



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    Toast.makeText(getContext(), error.getDescription(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Web Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.webView.loadUrl("https://iid");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WebViewViewModel.class);
        // TODO: Use the ViewModel
    }

}