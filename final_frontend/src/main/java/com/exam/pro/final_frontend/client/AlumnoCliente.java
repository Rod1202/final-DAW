package com.exam.pro.final_frontend.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.exam.pro.final_frontend.dto.AlumnoRequestDTO;
import com.exam.pro.final_frontend.dto.AlumnoResponseDTO;


@FeignClient(name = "busqueda-alumno",url = "http://localhost:8083")
public interface AlumnoCliente {

    @PostMapping("/inicio/buscar")
    ResponseEntity<AlumnoResponseDTO> busqueda(@RequestBody AlumnoRequestDTO alumnoRequest);



}