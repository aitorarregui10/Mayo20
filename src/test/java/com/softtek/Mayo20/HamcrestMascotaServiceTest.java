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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMascotaServiceTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {


        // Arrange(Preparar)
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "985764532");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);


        //Act (actuar)
        Mascota registrada = mascotaService.registrarMascota(mascota);

        //Assert (afirmar): Hamcrest

        //Verificar las propiedades de la mascota registrada
        assertThat(registrada, is(notNullValue()));
        assertThat(registrada.getNombre(), is(equalTo("Garfield")));
        assertThat(registrada.getPropietario(),is(notNullValue()));
        assertThat(registrada.getPropietario().getNombre(), is(equalTo("Dany")));
        assertThat(registrada.getPropietario().getCiudad(), is(equalTo("Lima")));
        assertThat(registrada.getPropietario().getTelefono(), is(equalTo("985764532")));
        assertThat(registrada, is(sameInstance(mascota)));
        assertThat(registrada, is(notNullValue()));


        //Verificar las propiedades del propietario con Hamcrest
        assertThat(registrada.getPropietario(),hasProperty("nombre", is("Dany")));
        assertThat(registrada.getPropietario(),hasProperty("ciudad", is("Lima")));
        assertThat(registrada.getPropietario(),hasProperty("telefono", is("985764532")));

        //Comprobar con más matchers de Hamcrest
        assertThat(registrada.getId(),is(greaterThanOrEqualTo(0)));

    }
}
