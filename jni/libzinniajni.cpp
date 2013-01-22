#include "zinnia.h"
#include <jni.h>
#include "libzinniajni.h"
#include <android/log.h>

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_new
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1new
  (JNIEnv *envenv, jobject jobj)
{
    zinnia_character_t* p = zinnia_character_new();
    return reinterpret_cast<jlong>(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1destroy
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    zinnia_character_destroy(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_set_value
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1set_1value
(JNIEnv *env, jobject jobj, jlong character, jstring jstr)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_set_value2
 * Signature: (JLjava/lang/String;J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1set_1value2
(JNIEnv *env, jobject jobj, jlong character, jstring jstr, jlong length)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_value
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1value
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return env->NewStringUTF(zinnia_character_value(p));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_set_width
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1set_1width
(JNIEnv *env, jobject jobj, jlong character, jlong width)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    zinnia_character_set_width(p, width);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_set_height
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1set_1height
(JNIEnv *env, jobject jobj, jlong character, jlong height)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    zinnia_character_set_height(p, height);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_width
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1width
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_width(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_height
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1height
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_height(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_clear
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1clear
(JNIEnv *env, jobject jobj, jlong stroke)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(stroke);
    zinnia_character_clear(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_add
 * Signature: (JJII)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1add
(JNIEnv *env, jobject jobj, jlong character, jlong id, jint x, jint y)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    zinnia_character_add(p, id, x, y);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_strokes_size
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1strokes_1size
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_strokes_size(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_stroke_size
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1stroke_1size
(JNIEnv *env, jobject jobj, jlong character, jlong id)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_stroke_size(p, id);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_x
 * Signature: (JJJ)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1x
(JNIEnv *env, jobject jobj, jlong character, jlong id, jlong index)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_x(p, id, index);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_y
 * Signature: (JJJ)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1y
(JNIEnv *env, jobject jobj, jlong character, jlong id, jlong index)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return zinnia_character_y(p, id, index);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_parse
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1parse
  (JNIEnv *env, jobject jobj, jlong character, jstring jstr)
{
	jint ret;
	const char *str = env->GetStringUTFChars(jstr, 0);
	__android_log_print(ANDROID_LOG_INFO, "sup", "doppie doo%s\n", str);//Or ANDROID_LOG_INFO, ...
	zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
	zinnia_character_parse(p, str);
	return ret;
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_parse2
 * Signature: (JLjava/lang/String;J)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1parse2
(JNIEnv *env, jobject jobj, jlong character, jstring str, jlong length)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    /* TODO */
    return 0;
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_to_string
 * Signature: (JLjava/lang/String;J)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1to_1string
(JNIEnv *env, jobject jobj, jlong character, jstring buf, jlong length)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    /* TODO */
    return 0;
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_character_strerror
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1character_1strerror
(JNIEnv *env, jobject jobj, jlong character)
{
    zinnia_character_t* p = reinterpret_cast<zinnia_character_t*>(character);
    return env->NewStringUTF(zinnia_character_strerror(p));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_result_value
 * Signature: (JJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1result_1value
(JNIEnv *env, jobject jobj, jlong result, jlong index)
{
    zinnia_result_t* p = reinterpret_cast<zinnia_result_t*>(result);
    //dickaboots
    __android_log_print(ANDROID_LOG_INFO, "hoo %s\n", zinnia_result_value(p, index));//Or ANDROID_LOG_INFO, ...
    return env->NewStringUTF(zinnia_result_value(p, index));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_result_score
 * Signature: (JJ)F
 */
JNIEXPORT jfloat JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1result_1score
(JNIEnv *env, jobject jobj, jlong result, jlong index)
{
    zinnia_result_t* p = reinterpret_cast<zinnia_result_t*>(result);
    return zinnia_result_score(p, index);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_result_size
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1result_1size
(JNIEnv *env, jobject jobj, jlong result)
{
    zinnia_result_t* p = reinterpret_cast<zinnia_result_t*>(result);
    return zinnia_result_size(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_result_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1result_1destroy
(JNIEnv *env, jobject jobj, jlong jlongv)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_new
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1new
  (JNIEnv *env, jobject jobj)
{
    zinnia_recognizer_t* p = zinnia_recognizer_new();
    return reinterpret_cast<jlong>(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1destroy
(JNIEnv *env, jobject jobj, jlong recognizer)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    zinnia_recognizer_destroy(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_open
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1open
  (JNIEnv *env, jobject jobj, jlong recognizer, jstring filename)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    return zinnia_recognizer_open(p, env->GetStringUTFChars(filename, NULL));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_open_from_ptr
 * Signature: (JLjava/lang/String;J)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1open_1from_1ptr
(JNIEnv *env, jobject jobj, jlong, jstring jstr, jlong jlongv)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_close
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1close
(JNIEnv *env, jobject jobj, jlong recognizer)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    return zinnia_recognizer_close(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_size
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1size
(JNIEnv *env, jobject jobj, jlong recognizer)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    return zinnia_recognizer_size(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_value
 * Signature: (JJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1value
(JNIEnv *env, jobject jobj, jlong recognizer, jlong index)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    return env->NewStringUTF(zinnia_recognizer_value(p, index));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_strerror
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1strerror
(JNIEnv *env, jobject jobj, jlong recognizer)
{
    zinnia_recognizer_t* p = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    return env->NewStringUTF(zinnia_recognizer_strerror(p));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_recognizer_classify
 * Signature: (JLjava/lang/String;J)J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1recognizer_1classify
(JNIEnv *env, jobject jobj, jlong recognizer, jlong character, jlong nbest)
{
    zinnia_recognizer_t* recog = reinterpret_cast<zinnia_recognizer_t*>(recognizer);
    zinnia_character_t* ch = reinterpret_cast<zinnia_character_t*>(character);
    zinnia_result_t* result = zinnia_recognizer_classify(recog, ch, nbest);
    return reinterpret_cast<jlong>(result);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_new
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1new
  (JNIEnv *env, jobject jobj)
{
   zinnia_trainer_t* trainer = zinnia_trainer_new();
   return reinterpret_cast<jlong>(trainer);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1destroy
(JNIEnv *env, jobject jobj, jlong trainer)
{
  zinnia_trainer_t* p = reinterpret_cast<zinnia_trainer_t*>(trainer);
  zinnia_trainer_destroy(p);

}


/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_add
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1add
(JNIEnv *env, jobject jobj, jlong trainer, jlong character)
{
  zinnia_trainer_t* p = reinterpret_cast<zinnia_trainer_t*>(trainer);
  zinnia_character_t* ch = reinterpret_cast<zinnia_character_t*>(character);
  return zinnia_trainer_add(p, ch);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_clear
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1clear
(JNIEnv *env, jobject jobj, jlong trainer)
{
  zinnia_trainer_t* p = reinterpret_cast<zinnia_trainer_t*>(trainer);
  zinnia_trainer_clear(p);
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_train
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1train
  (JNIEnv *env, jobject jobj, jlong trainer, jstring filename)
{
  zinnia_trainer_t* p = reinterpret_cast<zinnia_trainer_t*>(trainer);
  return zinnia_trainer_train(p, (const char*)(env->GetStringUTFChars(filename, NULL)));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_strerror
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1strerror
(JNIEnv *env, jobject jobj, jlong trainer)
{
  zinnia_trainer_t* p = reinterpret_cast<zinnia_trainer_t*>(trainer);
  return env->NewStringUTF(zinnia_trainer_strerror(p));
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_convert_model
 * Signature: (Ljava/lang/String;Ljava/lang/String;D)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1convert_1model
  (JNIEnv *env, jobject jobj, jstring txt_model, jstring binary_mode, jdouble compression_threshold)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    zinnia_trainer_make_header
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;D)I
 */
JNIEXPORT jint JNICALL Java_edu_clemson_kangaeru_Zinnia_zinnia_1trainer_1make_1header
(JNIEnv *env, jobject jobj, jstring txt_model, jstring header_file, jstring name, jdouble compression_threashold)
{
}

/*
 * Class:     edu_clemson_kangaeru_Zinnia
 * Method:    androidParse
 * Signature: ([B[BI)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_clemson_kangaeru_Zinnia_androidParse
  (JNIEnv *env, jobject string, jbyteArray charInput, jbyteArray modelInput, jint modelSize)
{
	//ignoring the input for getting things to work's sake
	//plan of action based on example.cpp in sdk folder of zinnia:
	//	1) make an input string formatted like the example did
	//	2) make a recognizer
	//	3) open a model file
	//		3a) get a byte array from java and treat it like a character array
	//		3b) use the zinnia_recognizer_open_from_ptr function
	//	4) make a character
	//	5) parse the input into said character
	//	6) make a result
	//	7) classify character with recognizer and put into result
	//	8) look at the results values
	//Have at it

	const char *input =
	  "(character (width 1000)(height 1000)"
	  "(strokes ((243 273)(393 450))((700 253)(343 486)(280 716)(393 866)(710 880))))";

	jbyte *model;
	model = env->GetByteArrayElements(modelInput, 0);
    char *modelData = (char *) model;

    size_t dataSize = (size_t) modelSize;

    //Actual work
    //const char *cret = androidParse(input, modelData, dataSize);
    zinnia_recognizer_t *z_recog = zinnia_recognizer_new();
    zinnia_recognizer_open_from_ptr(z_recog, modelData, dataSize);
    zinnia_character_t* z_char = zinnia_character_new();
    zinnia_character_parse(z_char, input);
    zinnia_result_t *z_res = zinnia_recognizer_classify(z_recog, z_char, 10);
    size_t size = zinnia_result_size(z_res);
   	//for now just the first
   	const char *cret = zinnia_result_value(z_res, 0);
   	__android_log_print(ANDROID_LOG_INFO, "sup", "%s\n", cret);//Or ANDROID_LOG_INFO, ...
    //
    jstring sret = (env)->NewStringUTF(cret);
	env->ReleaseByteArrayElements(modelInput, model, JNI_ABORT); /* abort to not copy back contents */

	return sret;
}
