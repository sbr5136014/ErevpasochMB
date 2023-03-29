package com.sbr.ep_mb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.sbr.ep_mb.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    Printer mPrinter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        try {
             mPrinter = new Printer(Printer.TM_T88, Printer.MODEL_ANK, getContext());
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }
        try {
            mPrinter.addTextFont(Printer.FONT_A);
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }
        try {
            mPrinter.addText("hi");
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }
        try {
            mPrinter.sendData(10);
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}