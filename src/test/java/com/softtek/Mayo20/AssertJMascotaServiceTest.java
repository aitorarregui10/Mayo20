package com.softtek.Mayo20;

import com.softtek.Mayo20.modelo.Mascota;
import com.softtek.Mayo20.modelo.Propietario;
import com.softtek.Mayo20.repository.MascotaRepository;
import com.softtek.Mayo20.repository.MascotaRepositoryImpl;
import com.softtek.Mayo20.service.ExternalService;
import com.softtek.Mayo20.service.ExternalServiceImpl;
import com.softtek.Mayo20.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AssertJMascotaServiceTest {


    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {


        // Arrange(Preparar)
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        MascotaService mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        Mascota registrada = mascotaService.registrarMascota(mascota);
        assertThatThrownBy(() -> mascotaService.registrarMascota(registrada));


        assertThat(registrada).isNotNull();
        assertThat(registrada.getNombre()).isEqualTo("Garfield");
        assertThat(registrada.getPropietario()).isNotNull();
        assertThat(registrada.getPropietario().getNombre()).isEqualTo("Dany");
        assertThat(registrada.getPropietario().getCiudad()).isEqualTo("Lima");
        assertThat(registrada.getPropietario().getTelefono()).isEqualTo("987654321");
        assertThat(registrada).isSameAs(mascota);
        assertThat(registrada.getId()).isPositive();

        Optional<Mascota> mascoton = mascotaService.buscarMascotaPorId(mascota.getId());
        mascotaService.eliminarMascotaPorId(mascota.getId());

        Mascota mascotaSinNombre = new Mascota();
        mascotaSinNombre.setNombre(null);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotaSinNombre));

        Mascota mascotaSinPropietario = new Mascota();
        mascotaSinPropietario.setNombre("Paco");
        mascotaSinPropietario.setPropietario(null);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotaSinPropietario));

        Propietario propietarioSinTelefono = new Propietario();
        propietarioSinTelefono.setNombre("Juan");
        propietarioSinTelefono.setCiudad("Madrid");
        propietarioSinTelefono.setTelefono(null);
        Mascota mascotaSinTelefono = new Mascota();
        mascotaSinTelefono.setNombre("Paquito");
        mascotaSinTelefono.setPropietario(propietarioSinTelefono);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotaSinTelefono));

        Mascota mascotaSinVacunas  = new Mascota();
        mascotaSinVacunas.setNombre("Paco sin vacuna");
        mascotaSinVacunas.setPropietario(propietario);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotaSinVacunas));

        Mascota mascotaSinMunicipio  = new Mascota();
        mascotaSinMunicipio.setNombre("Paco no registrado");
        mascotaSinMunicipio.setPropietario(propietario);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotaSinMunicipio));

    }
}
