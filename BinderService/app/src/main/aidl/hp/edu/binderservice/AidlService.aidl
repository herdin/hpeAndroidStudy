// AidlService.aidl
package hp.edu.binderservice;

// Declare any non-default types here with import statements

interface AidlService { //프로세스간 통신을 위한 인터페이스
    /*
    AidlService 파일을 만들고 리빌드를 하면 같은이름의 자바파일이 나오게된다.
    프로젝트에선 안보이므로 리소스찾기로 보면됨 근데 안봐도됨..
    */
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString); //얘는 필요가없어서 주석
    String onPlay(int flag); //사용할 함수
    String onStop(int flag);
    String onPause(int flag);
}
