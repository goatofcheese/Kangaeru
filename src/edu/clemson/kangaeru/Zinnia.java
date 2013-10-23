package edu.clemson.kangaeru;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.util.Arrays;

import android.content.res.AssetManager;

public class Zinnia {

	/* This entire thing was a horrible idea and I hope to never revist it for the rest of my days.
	 * If you ever find yourself looking at this code. Ignore this until you figure out how to do conversion from c++
	 * to java data types and file point
	 *  */
	
	public byte[] buffer;
	public int modelSize;
	
	Zinnia(AssetManager am){
		buffer = null;
		// To load text file
        InputStream input;
        try {
            input = am.open("handwriting-ja.model.txt");
             
            modelSize = input.available();
            //buffer = new byte[modelSize];
            buffer = new byte[1024];
            input.read(buffer);
            input.close();
 
        } catch (IOException e) {
        	System.err.println("I/O exception man");
            e.printStackTrace();
        }
        System.err.println("I read this first " + buffer[0]);
        String derp = Arrays.toString(buffer);
        System.err.println("and here's the string: " + derp);
        byte[] derperp = null;
        try {
			derperp = derp.getBytes("UTF-8");
			//should parse here
			//	parse();
			derp = new String(derperp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//cry yourself to sleep
			e.printStackTrace();
		}
        String x = "\u82e6";
        System.err.println(x);
	}
	
	static {
		System.loadLibrary("zinniajni");
	}
	

	public native long  	zinnia_character_new();
	public native void		zinnia_character_destroy(long character);
	public native void		zinnia_character_set_value(long character, String str);
	public native void		zinnia_character_set_value2(long character, String str, long length);
	public native String	zinnia_character_value(long character);
	public native void		zinnia_character_set_width(long character, long width);
	public native void		zinnia_character_set_height(long character, long height);
	public native long      zinnia_character_width(long character);
	public native long      zinnia_character_height(long character);
	public native void		zinnia_character_clear(long stroke);
	public native int		zinnia_character_add(long character, long id, int x, int y);
	public native long      zinnia_character_strokes_size(long character);
	public native long      zinnia_character_stroke_size(long character, long id);
	public native int		zinnia_character_x(long character, long id, long i);
	public native int		zinnia_character_y(long character, long id, long i);
	public native int		zinnia_character_parse(long character, String str);
	public native int		zinnia_character_parse2(long character, String str, long length);
	public native int		zinnia_character_to_string(long character, String buf, long length);
	public native String 	zinnia_character_strerror(long character);


	public native String 	zinnia_result_value(long result, long i);
	public native float		zinnia_result_score(long result, long i);
	public native long      zinnia_result_size(long result);
	public native void		zinnia_result_destroy(long result);

	public native long		zinnia_recognizer_new();
	public native void		zinnia_recognizer_destroy(long recognizer);
	public native int		zinnia_recognizer_open(long recognizer, String filename);
	public native int		zinnia_recognizer_open_from_ptr(long recognizer, String ptr, long size);
	public native int		zinnia_recognizer_close(long recognizer);
	public native long		zinnia_recognizer_size(long recognizer);
	public native String 	zinnia_recognizer_value(long recognizer, long i);
	public native String	zinnia_recognizer_strerror(long recognizer);
	public native long 		zinnia_recognizer_classify(long recognizer, long character, long nbest);

	public native long		zinnia_trainer_new();
	public native void      zinnia_trainer_destroy(long trainer);
	public native int       zinnia_trainer_add(long trainer, long character);
	public native void		zinnia_trainer_clear(long trainer);
	public native int		zinnia_trainer_train(long trainer, String filename);
	public native String	zinnia_trainer_strerror(long trainer);
	public native int		zinnia_trainer_convert_model(String txt_model, String binary_model,	double compression_threshold);
	public native int		zinnia_trainer_make_header(String txt_model,
							String header_file,
							String name,
							double compression_threshold);
	public native String    androidParse(byte[] charInput, byte[] modelInput, int modelSize);
}
