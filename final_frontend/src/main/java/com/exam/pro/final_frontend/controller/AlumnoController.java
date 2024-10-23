package com.exam.pro.final_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exam.pro.final_frontend.client.AlumnoCliente;
import com.exam.pro.final_frontend.dto.AlumnoRequestDTO;
import com.exam.pro.final_frontend.dto.AlumnoResponseDTO;
import com.exam.pro.final_frontend.model.Alumno;
@Controller
@RequestMapping("/busqueda")
public class AlumnoController {

    @Autowired
    AlumnoCliente alumnoClient;


    @GetMapping("/buscarAlumno")
    public String mostrarFormularioBusqueda(Model model) {
        Alumno alumnoModel=new Alumno("","","","","","");
        model.addAttribute("alumnoModel",alumnoModel);
        return "busqueda"; // Muestra la página de búsqueda
    }

    @PostMapping("/buscar")
    public String buscarAlumno(@RequestParam("codigo") String codigo, Model model) {
        System.out.println("Consumiendo FeignClient con código: " + codigo);
    
        if (codigo == null || codigo.trim().isEmpty()) {
            Alumno alumnoModel = new Alumno("01", "", "", "", "", "Por favor ingrese un código válido.");
            model.addAttribute("alumnoModel", alumnoModel);
            return "busqueda";
        }
    
        try {
            AlumnoRequestDTO alumnoRequest = new AlumnoRequestDTO(codigo);
            ResponseEntity<AlumnoResponseDTO> responseEntity = alumnoClient.busqueda(alumnoRequest);
    
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                AlumnoResponseDTO alumnoResponse = responseEntity.getBody();
    
                if (alumnoResponse.codigo() != null) {
                    Alumno alumnoModel = new Alumno(
                            alumnoResponse.codigo(),
                            alumnoResponse.nombre(),
                            alumnoResponse.apellido(),
                            alumnoResponse.carrera(),
                            alumnoResponse.ciclo(),
                            ""); // Sin mensaje de error, datos encontrados
                    model.addAttribute("alumnoModel", alumnoModel);
                    return "detalle"; // Muestra la página de detalles del alumno
                } else {
                    model.addAttribute("error", "No se encontraron resultados para el código ingresado.");
                    return "busqueda"; // Vuelve a la misma página con el error
                }
            } else {
                model.addAttribute("error", "No se encontraron resultados.");
                return "busqueda"; // Vuelve a la misma página con el error
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al consumir el servicio.");
            return "busqueda"; // Vuelve a la misma página con el error
        }
    }

}
