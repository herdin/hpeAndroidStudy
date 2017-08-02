// AidlService.aidl
package hp.edu.binderservice;

// Declare any non-default types here with import statements

interface AidlService { //프로세스간 통신을 위한 인터페이스
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
    String onPlay(int flag);
    String onStop(int flag);
    String onPause(int flag);
}
