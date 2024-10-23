package com.exam.pro.final_backend.service;

import java.io.IOException;
import com.exam.pro.final_backend.dto.AlumnoRequest;


public interface AlumnoService {

    
    String[] buscarAlumno(AlumnoRequest alumnoRequest) throws IOException;
  
}
