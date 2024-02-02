/*
 * ABRE O BLOCO DE SCRIPT
*/

        function mudarCor(valor) {
            var resultadoElemento = document.getElementById('idResultado');
			
			var valorNumerico = parseFloat(valor);
			
			if (valor < 0) {
                resultadoElemento.style.color = 'red';
            } else {
                resultadoElemento.style.color = 'green';
            }

            // Atualizando o texto
            console.log(`Resultado: ${valorNumerico}`);
        }