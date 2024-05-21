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

import static org.junit.jupiter.api.Assertions.*;

public class JUnitMascotaServiceTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente(){



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
    mascotaService.eliminarMascotaPorId(mascota.getId());

    //Assert(Afirmar) : JUnit

    //Verifica que el objeto no es null
    assertNotNull(registrada, "La mascota registradad no debería ser null.");

    //Verifica que la mascota registrada e ingresada son la misma
    assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la ingresada.");

    //Verifica que el valor esperado coincida con el actual
    assertEquals("Garfield", registrada.getNombre(), "El nombre de la mascota registrada debería ser Garfield.");

    //Confirma que el propietario de la mascita registrada es el mismo que se proporcionó
    assertSame(propietario, registrada.getPropietario(), "El propietario de la mascota registrada debería ser el mismo que el ingresado");

    //Comprueba los detalles del propietario para garantizar la precision de los datos.
    assertEquals("Dany", registrada.getPropietario().getNombre(), "El nombre del propietario debería ser 'Dany'.");
    assertEquals("Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietario debería ser 'Lima'.");
    assertEquals("985764532", registrada.getPropietario().getTelefono(), "El teléfono del propietaio debería ser'985764532'.");

    //Comprobar con más aserciones de JUnit 5
    assertAll("Propiedades de la mascota",
            () -> assertEquals("Garfield", registrada.getNombre(), "El nombre debería coincidir."),
            () -> assertNotNull(registrada.getPropietario(), "El propietario no debe ser null."),
            () -> assertEquals("Dany", registrada.getPropietario().getNombre(), "El nombre del propietario debe ser 'Dany'."),
            () -> assertEquals("Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietario debería ser 'Lima'."),
            () -> assertEquals("985764532", registrada.getPropietario().getTelefono(), "El teléfono del propietaio debería ser'985764532'.")
    );
/*
    //Comprobar método buscar por ID
    Optional<Mascota> mascoton = mascotaService.buscarMascotaPorId(mascota.getId());
    assertTrue(mascoton.isPresent());
    Optional<Mascota> mascotaEliminada = mascotaService.buscarMascotaPorId(mascota.getId());
    assertTrue(mascotaEliminada.isPresent());

    */

/*
    //Comprobar que la mascota ya no está después de eliminar
    assertThrows(IllegalArgumentException.class, () -> {
        mascotaService.buscarMascotaPorId(mascota.getId());
        }, "No se pudo encontrar la mascota con el ID proporcionado.");
*/


    //Comprueba que las siguientes operaciones no lanzan excepciones , lo cual es útil para confirmar que propiedades esenciales están presentes
    assertDoesNotThrow(() -> registrada.getNombre(),"Obtener el nombre de la mascota no debería lanzar una ezcepción.");
    assertDoesNotThrow(() -> registrada.getPropietario().getCiudad(), "Obtener la ciudad del propietario no debería lanzar una excepción.");

    /*

    //Comprueba el throw new de registrar mascota
        Mascota mascota2 = new Mascota();
        mascota.setNombre("");
    assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.registrarMascota(mascota2);
        }, "Se esperaba una excepción IllegalArgumentException al registrar una mascota con nombre vacío.");

    */

    /*
    //Comprueba el throw new del propietario
    Propietario propietario2 = new Propietario();
    propietario2.setNombre("");
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
       mascotaService.buscarMascotaPorId(1);
    }, "No se lanzó la excepción esperada.");

    assertEquals("La mascota debe tener un propietario.", exception.getMessage());
     */


    //Comprueba el throw new del teléfono del propietario
    Propietario propietario2 = new Propietario();
    propietario2.setTelefono("");
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
       mascotaService.buscarMascotaPorId(1);
    }, "No se lanzó la excepción esperada.");

    assertEquals("La mascota debe tener un propietario.", exception.getMessage());

    }


}
