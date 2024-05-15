package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controllerv1 {
    //서블렛과 이름이 똑같은 인터페이스
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
