package com.exam.pro.final_backend.controller;

import com.exam.pro.final_backend.dto.AlumnoRequest;
import com.exam.pro.final_backend.dto.AlumnoResponse;
import com.exam.pro.final_backend.service.AlumnoService;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import java.io.IOException;


@RestController
@RequestMapping("/inicio")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @PostMapping("/buscar")
    public AlumnoResponse buscando(@RequestBody AlumnoRequest alumnoRequest){


        try {
            String[] datosAlumno=alumnoService.buscarAlumno(alumnoRequest);


            if(datosAlumno == null){
                return new AlumnoResponse("01","","","","");
            }

                return new AlumnoResponse(datosAlumno[0],datosAlumno[1],datosAlumno[2],datosAlumno[3],datosAlumno[4]);

        }catch (IOException e){

            return new AlumnoResponse("99","","","","");
        }

    }







    }