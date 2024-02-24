/*
 * ABRE O BLOCO DE SCRIPT
*/

function mudarCor(valor) {
	var resultadoElemento = document.getElementById('idResultado');

	if (valor < 0) {
		resultadoElemento.style.color = 'red';
	} else {
		resultadoElemento.style.color = 'green';
	}
}