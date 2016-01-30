package br.com.dreambox.listener;

import android.view.View;

import br.com.dreambox.model.Dream;

public abstract class OnDreamClickListener {

    public abstract View.OnClickListener onClick(Dream dream);

}
