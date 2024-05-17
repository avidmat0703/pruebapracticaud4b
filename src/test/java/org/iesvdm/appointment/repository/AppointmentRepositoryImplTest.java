package org.iesvdm.appointment.repository;

import org.iesvdm.appointment.entity.Appointment;
import org.iesvdm.appointment.entity.Customer;
import org.iesvdm.appointment.repository.impl.AppointmentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class AppointmentRepositoryImplTest {

    private Set<Appointment> appointments;

    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setup() {
        appointments = new HashSet<>();
        appointmentRepository = new AppointmentRepositoryImpl(appointments);
    }

    /**
     * Crea 2 citas (Appointment) una con id 1 y otra con id 2,
     * resto de valores inventados.
     * Agrégalas a las citas (appointments) con la que
     * construyes el objeto appointmentRepository bajo test.
     * Comprueba que cuando invocas appointmentRepository.getOne con uno
     * de los id's anteriores recuperas obtienes el objeto.
     * Pero si lo invocas con otro id diferente recuperas null
     */
    @Test
    void getOneTest() {
        Appointment apointment1 = new Appointment();
        apointment1 = appointmentRepository.getOne(1);
        Appointment apointment2 = new Appointment();
        apointment2 = appointmentRepository.getOne(2);
        appointments.add(apointment1);
        appointments.add(apointment2);
        assertThat(appointmentRepository.getOne(1)).isEqualTo(apointment1);
        assertThat(appointmentRepository.getOne(20)).isEqualTo(apointment1);
    } //Cuando creo las los instancias, y las meto en el objeto appoinments luego intento llamarlas y me dicen que se encuentran null, cuando las he creado al principio.

    /**
     * Crea 2 citas (Appointment) y guárdalas mediante
     * appointmentRepository.save.
     * Comprueba que la colección appointments
     * contiene sólo esas 2 citas.
     */
    @Test
    void saveTest() {
        Appointment apointment1 = new Appointment();
        apointment1 = appointmentRepository.getOne(1);
        Appointment apointment2 = new Appointment();
        apointment2 = appointmentRepository.getOne(2);
        appointments.add(apointment1);
        appointments.add(apointment2);
        appointmentRepository.save(apointment1);
        appointmentRepository.save(apointment2);
        assertThat(appointments.contains(apointment1)).isTrue();
        assertThat(appointments.contains(apointment2)).isTrue();
    }

    /**
     * Crea 2 citas (Appointment) una cancelada por un usuario y otra no,
     * (atención al estado de la cita, lee el código) y agrégalas mediante
     * appointmentRepository.save a la colección de appointments
     * Comprueba que mediante appointmentRepository.findCanceledByUser
     * obtienes la cita cancelada.
     */
    @Test
    void findCanceledByUserTest() {
        Appointment apointment1 = new Appointment();
        apointment1 = appointmentRepository.getOne(1);
        Customer customer1 = new Customer();
        customer1.setId(1);
        apointment1.setCustomer(customer1);
        appointments.add(apointment1);
        apointment1.setCustomer(customer1);

        Appointment apointment2 = new Appointment();
        apointment2 = appointmentRepository.getOne(2);
        Customer customer2 = new Customer();
        customer2.setId(2);
        apointment2.setCustomer(customer2);
        appointments.add(apointment2);
        appointmentRepository.save(apointment2);

        assertThat(appointmentRepository.findCanceledByUser(1)).isEqualTo(apointment1);
    }

    /**
     * Crea 3 citas (Appointment), 2 para un mismo cliente (Customer)
     * con sólo una cita de ellas presentando fecha de inicio (start)
     * y fin (end) dentro del periodo de búsqueda (startPeriod,endPeriod).
     * Guárdalas mediante appointmentRepository.save.
     * Comprueba que appointmentRepository.findByCustomerIdWithStartInPeroid
     * encuentra la cita en cuestión.
     * Nota: utiliza LocalDateTime.of(...) para crear los LocalDateTime
     */
    @Test
    void findByCustomerIdWithStartInPeroidTest() {

    }


    /**
     * Crea 2 citas (Appointment) una planificada (SCHEDULED) con tiempo fin
     * anterior a la tiempo buscado por appointmentRepository.findScheduledWithEndBeforeDate
     * guardándolas mediante appointmentRepository.save para la prueba de findScheduledWithEndBeforeDate
     *
     */
    @Test
    void findScheduledWithEndBeforeDateTest() {

    }


    /**
     * Crea 3 citas (Appointment) planificadas (SCHEDULED)
     * , 2 para un mismo cliente, con una elegible para cambio (con fecha de inicio, start, adecuada)
     * y otra no.
     * La tercera ha de ser de otro cliente.
     * Guárdalas mediante appointmentRepository.save
     * Comprueba que getEligibleAppointmentsForExchange encuentra la correcta.
     */
    @Test
    void getEligibleAppointmentsForExchangeTest() {

    }


    /**
     * Igual que antes, pero ahora las 3 citas tienen que tener
     * clientes diferentes y 2 de ellas con fecha de inicio (start)
     * antes de la especificada en el método de búsqueda para
     * findExchangeRequestedWithStartBefore
     */
    @Test
    void findExchangeRequestedWithStartBeforeTest() {

    }
}
