function chartExtender() {
	//copy the config options into a variable
	var options = $.extend(true, {}, this.cfg.config);

	options = {
		options: {
			plugins: {
				title: {
					display: true,
					font: {
						size:16
					}
				},
				legend: {
					display: true,
					position: 'bottom'
				},
				tooltip: {
					
					callbacks: {
						beforeTitle: function() {
							return 'Data: '
						},
						label: function(context) {
							let label = (' ' + context.dataset.label) || '';

							if (label) {
								label += ': ';
							}
							if (context.parsed.y !== null) {
								label += new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(context.parsed.y);
							}
							return label;
						}
					}
				}
			},
			scales: {
				y: {
					ticks: {
						// Include a dollar sign in the ticks
						callback: function(value) {
							return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value);
						}
					}
				}
			}
		}
	}	
	//merge all options into the main chart options
	$.extend(true, this.cfg.config, options);
	};