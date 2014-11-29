/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.colorpicker;

import de.eisfeldj.augendiagnose.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Creates a circular swatch of a specified color. Adds a checkmark if marked as checked.
 */
public class ColorPickerSwatch extends FrameLayout implements View.OnClickListener {
	/**
	 * The color of the swatch.
	 */
	private int mColor;

	/**
	 * View for the image.
	 */
	private ImageView mSwatchImage;

	/**
	 * View for the checkmark.
	 */
	private ImageView mCheckmarkImage;

	/**
	 * Listener for selection the swatch.
	 */
	private OnColorSelectedListener mOnColorSelectedListener;

	/**
	 * Interface for a callback when a color square is selected.
	 */
	public interface OnColorSelectedListener {

		/**
		 * Called when a specific color square has been selected.
		 *
		 * @param color
		 *            the selected color.
		 */
		void onColorSelected(int color);
	}

	/**
	 * Constructor initializing the swatch.
	 *
	 * @param context
	 *            The application context.
	 * @param color
	 *            The color of the swatch.
	 * @param checked
	 *            Flag indicating if the swatch is flagged.
	 * @param listener
	 *            A listener called if the swatch is selected.
	 */
	public ColorPickerSwatch(final Context context, final int color, final boolean checked,
			final OnColorSelectedListener listener) {
		super(context);
		mColor = color;
		mOnColorSelectedListener = listener;

		LayoutInflater.from(context).inflate(R.layout.color_picker_swatch, this);
		mSwatchImage = (ImageView) findViewById(R.id.color_picker_swatch);
		mCheckmarkImage = (ImageView) findViewById(R.id.color_picker_checkmark);
		setColor(color);
		setChecked(checked);
		setOnClickListener(this);
	}

	/**
	 * Set the color of the swatch.
	 *
	 * @param color
	 *            The color to be set.
	 */
	private void setColor(final int color) {
		Drawable[] colorDrawable = new Drawable[]
		{ getContext().getResources().getDrawable(R.drawable.color_picker_swatch) };
		mSwatchImage.setImageDrawable(new ColorStateDrawable(colorDrawable, color));
	}

	/**
	 * Set the checked flag of the swatch.
	 *
	 * @param checked
	 *            The falue of the flag.
	 */
	private void setChecked(final boolean checked) {
		if (checked) {
			mCheckmarkImage.setVisibility(View.VISIBLE);
		}
		else {
			mCheckmarkImage.setVisibility(View.GONE);
		}
	}

	@Override
	public final void onClick(final View v) {
		if (mOnColorSelectedListener != null) {
			mOnColorSelectedListener.onColorSelected(mColor);
		}
	}
}