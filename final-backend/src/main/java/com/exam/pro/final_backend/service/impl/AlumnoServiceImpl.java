package com.exam.pro.final_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.exam.pro.final_backend.service.AlumnoService;
import com.exam.pro.final_backend.dto.AlumnoRequest;
@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    ResourceLoader resourceLoader;
    @Override
    public String[] buscarAlumno(AlumnoRequest alumnoRequest) throws IOException{
        String[] datosAlumno = null;
        Resource resource = resourceLoader.getResource("classpath:alumnos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (alumnoRequest.codigo().equals(datos[0])) {
                    datosAlumno = datos; // Recuperar todos los datos del alumno
                    break; // Salir del bucle una vez que el alumno ha sido encontrado
                }
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo", e);
        }

        // Simular tiempo de respuesta de 5 segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return datosAlumno;
    }
}
