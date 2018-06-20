LOCAL_PATH := $(call my-dir)

include $(call all-makefiles-under,$(LOCAL_PATH))



PRODUCT_COPY_FILES += \
	vendor/tpv/lib/libasound.so:system/lib/libasound.so\ 
	vendor/tpv/lib/libapedec.so:system/lib/libapedec.so\ 
	vendor/tpv/lib/libart-compiler.so:system/lib/libart-compiler.so\ 
	vendor/tpv/lib/libaudioflinger.so:system/lib/libaudioflinger.so\ 
	vendor/tpv/lib/libaudioeffect_jni.so:system/lib/libaudioeffect_jni.so\ 
	vendor/tpv/lib/libasan_preload.so:system/lib/libasan_preload.so\ 
	vendor/tpv/lib/libart.so:system/lib/libart.so
