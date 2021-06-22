package com.febryan.webview.ui.youtube;

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
import com.febryan.webview.databinding.YoutubeFragmentBinding;

public class YoutubeFragment extends Fragment {

    private YoutubeFragmentBinding binding;

    private YoutubeViewModel mViewModel;

    public static YoutubeFragment newInstance() {
        return new YoutubeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.youtube_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = YoutubeFragmentBinding.bind(view);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Opening youtube...");
        binding.webViewYoutube.getSettings().setJavaScriptEnabled(true);


        binding.webViewYoutube.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressDialog.dismiss();

                binding.imgError.setVisibility(View.VISIBLE);
                binding.tvError.setVisibility(View.VISIBLE);

                binding.webViewYoutube.setVisibility(View.GONE);
//                binding.webView.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    Toast.makeText(getContext(), error.getDescription(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Web Error", Toast.LENGTH_SHORT).show();
                }
            }

        });

        binding.webViewYoutube.loadUrl("https://yjhghg.com");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(YoutubeViewModel.class);
        // TODO: Use the ViewModel
    }

}