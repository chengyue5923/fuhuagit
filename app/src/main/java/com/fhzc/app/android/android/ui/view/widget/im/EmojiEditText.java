package com.fhzc.app.android.android.ui.view.widget.im;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;



public class EmojiEditText extends EditText {

    public EmojiEditText(Context context) {
        super(context);
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
//        ClipboardManager clip = (ClipboardManager)getContext()
//                .getSystemService(Context.CLIPBOARD_SERVICE);
//        if (id == android.R.id.paste) {
//            Editable text = getText();
//            String str = (text != null) ? text.toString() : "";
//            CharSequence pasteCharSequence = (clip != null) ? clip.getText() : null;
//            if (!TextUtils.isEmpty(pasteCharSequence)) {
//                str = str + pasteCharSequence.toString();
//            }
//            setText(EmojiParser.getInstance(getContext()).convertToHtml(str, getContext()));
//            setSelection(getText().length());
//            return true;
//        }
        return super.onTextContextMenuItem(id);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
