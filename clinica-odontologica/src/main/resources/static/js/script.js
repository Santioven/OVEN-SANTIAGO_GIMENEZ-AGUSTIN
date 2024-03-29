document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registroForm');
    const pacientesList = document.getElementById('pacientesList');

    // Función para obtener y mostrar la lista de pacientes
    function listarPacientes() {
        fetch('http://localhost:8081/pacientes')
            .then(response => response.json())
            .then(data => {
                // Limpiar la lista de pacientes
                pacientesList.innerHTML = '';

                // Iterar sobre cada paciente y agregarlo a la lista
                data.forEach(paciente => {
                    const pacienteItem = document.createElement('div');
                    pacienteItem.classList.add('paciente-item');
                    pacienteItem.innerHTML = `
                        <p class="paciente-id">ID: ${paciente.id}</p>
                        <p><strong>Nombre:</strong> ${paciente.nombre} ${paciente.apellido}</p>
                        <p><strong>DNI:</strong> ${paciente.dni}</p>
                        <p><strong>Fecha de Ingreso:</strong> ${paciente.fechaIngreso}</p>
                        <p><strong>Domicilio:</strong> ${paciente.domicilioSalidaDto ? `${paciente.domicilioSalidaDto.calle} ${paciente.domicilioSalidaDto.numero}, ${paciente.domicilioSalidaDto.localidad}, ${paciente.domicilioSalidaDto.provincia}` : 'Sin domicilio registrado'}</p>
                    `;
                    pacientesList.appendChild(pacienteItem);
                });
            })
            .catch(error => {
                console.error('Error al obtener la lista de pacientes:', error);
            });
    }

    // Llamar a la función para listar pacientes cuando se carga la página
    listarPacientes();

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevenir el envío del formulario por defecto

        // Obtener los valores del formulario
        const nombre = document.getElementById('inputNombre').value;
        const apellido = document.getElementById('inputApellido').value;
        const dni = document.getElementById('inputDNI').value;
        const fechaIngreso = document.getElementById('inputIngreso').value;
        const calle = document.getElementById('inputCalle').value;
        const ciudad = document.getElementById('inputCiudad').value;
        const provincia = document.getElementById('inputProvincia').value;
        const numero = document.getElementById('inputNumero').value;

        // Crear objeto con los datos del paciente
        const paciente = {
            nombre: nombre,
            apellido: apellido,
            dni: dni,
            fechaIngreso: fechaIngreso,
            domicilioEntradaDto: {
                calle: calle,
                numero: numero,
                localidad: ciudad,
                provincia: provincia
            }
        };

        // Realizar la solicitud AJAX para registrar un nuevo paciente
        fetch('http://localhost:8081/pacientes/registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        })
        .then(response => response.json())
        .then(data => {
            // Manejar la respuesta del servidor (opcional)
            console.log(data);
            // Listar los pacientes actualizados después de registrar uno nuevo
            listarPacientes();
        })
        .catch(error => {
            console.error('Error al registrar el paciente:', error);
        });

        // Limpiar los campos del formulario después de registrar al paciente
        form.reset();
    });
});