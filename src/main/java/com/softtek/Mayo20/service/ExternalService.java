package com.softtek.Mayo20.service;


import com.softtek.Mayo20.modelo.Mascota;

public interface ExternalService {

    boolean validarVacunas(Mascota mascota);
    boolean verificarRegistroMunicipal(Mascota mascota);
}