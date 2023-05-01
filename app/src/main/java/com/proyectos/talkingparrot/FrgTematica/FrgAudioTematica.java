package com.proyectos.talkingparrot.FrgTematica;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectos.talkingparrot.R;

public class FrgAudioTematica extends Fragment {

    public FrgAudioTematica() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_audio_tematica, container, false);
    }
}