package com.softtek.Mayo20.repository;

import com.softtek.Mayo20.modelo.Mascota;

import java.util.Optional;

public interface MascotaRepository {
    Mascota guardar(Mascota mascota);
    Optional<Mascota> findById(Integer id);
    void deleteById(Integer id);
}
