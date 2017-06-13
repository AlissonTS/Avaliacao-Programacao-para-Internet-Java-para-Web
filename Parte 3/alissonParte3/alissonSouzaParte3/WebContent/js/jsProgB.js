/**
 * 
 */
			// var item = [];
			var totalItem = [];
			
			function Redireciona(){
				location.href=opcoes.value;
			}
			
			function Valor(){
				var str = produtos.value;
				var res = str.split(","); 
				var valor = parseInt(document.getElementById('quantidade').value, 10);
				
				document.getElementById('precoUnitario').innerHTML = res[2];
				document.getElementById('descricao').innerHTML = res[1];
				document.getElementById('codigo').innerHTML = res[0];
				
				// document.getElementById('precoUnitario').value = produtos.value;
				
				var total = valor * res[2];
				
				if(total>0){
					document.getElementById('totalItem').innerHTML = total;
				}
			}
			
			function calcular(){
			    var valor1 = parseFloat(document.getElementById('precoUnitario').innerHTML);
			    var valor2 = parseInt(document.getElementById('quantidade').value, 10);
			    var valor3 = parseInt(document.getElementById('codigo').value, 10);
			    
			    var total = valor1 * valor2;
			    
			    if(total>0){
			    	 document.getElementById('totalItem').innerHTML = total;
			    }
			    
			}
			
			jQuery(function($){
			       $("#data").mask("99/99/9999");
			});
			
			jQuery(function($) {
				  adicionar = function() {
					
					var quantidade = parseInt(document.getElementById("quantidade").value, 10);
					var total = parseFloat(document.getElementById('totalItem').innerHTML);
					var descricao = document.getElementById("descricao").innerHTML;
					var codigo = document.getElementById("codigo").innerHTML;
					
					if(quantidade>0 & total>0 & descricao!=null & codigo>0){
						// item.push([codigo, descricao, quantidade, total]);
						totalItem.push(total);
						
						// var tamanho = item.length;	
						
						var tamanho = totalItem.length;	
						
						var totalcompra=0;
							
						for (i = 0; i <tamanho; i++) {	
								totalcompra=totalcompra+totalItem[i];
						} 
						
						var arredondado = parseFloat(totalcompra.toFixed(2));
						
						document.getElementById('total').innerHTML = arredondado;
						
						
						var newRow = $("<tr>");
					    var cols = "";
					    	
					    cols += '<td>'+codigo+'<input form="InserirVenda" value="'+codigo+','+quantidade+', '+total+', '+quantidade+'" style="display: none" type="checkbox" name="item" checked></td>';
					    cols += '<td>'+descricao+'</td>';
					    cols += '<td>'+quantidade+'</td>';
					    cols += '<td>'+total+'</td>';
					    cols += '<td>';
					    cols += '<button class="btn btn-warning" onclick="removerLinha(this, '+total+')" type="button">Remover</button>';
					    cols += '</td>';
					    
					    cols += '</tr>';
					    
					    newRow.append(cols);
					    $("#produtos-tabela").append(newRow);
					    
					    contaLinhasTabela();
					}
					
				    return false;
				  };
				  
				  removerLinha = function(handler, total) {
				    var tr = $(handler).closest('tr');
				    
				    tr.fadeOut(400, function(){ 
				    	tr.remove();
				      
				    	index = totalItem.indexOf(total);
				      
						totalItem.splice(index, 1);
						
						var tamanho = totalItem.length;	
						
						var totalcompra=0;
						
						for (i = 0; i <tamanho; i++) {	
								totalcompra=totalcompra+totalItem[i];
						} 
							
						var arredondado = parseFloat(totalcompra.toFixed(2));
						
						document.getElementById('total').innerHTML = arredondado;	  
						  
						contaLinhasTabela();
				      
				    }); 

				    return false;
				  };
				  
				})(jQuery);
			
			function contaLinhasTabela(){
				var tabela = document.getElementById('produtos-tabela');
				var linhas = tabela.getElementsByTagName('tr');
				
				document.getElementById('linhas').innerHTML = linhas.length-1;
			}
			
			/*
		
			jQuery(function($) {
				  adicionar = function() {
					
					var quantidade = parseInt(document.getElementById("quantidade").value, 10);
					var total = parseFloat(document.getElementById('totalItem').innerHTML);
					var descricao = document.getElementById("descricao").innerHTML;
					var codigo = document.getElementById("codigo").innerHTML;
					
					if(quantidade>0 & total>0 & descricao!=null & codigo>0){
						// item.push([codigo, descricao, quantidade, total]);
						
						var tamanho = totalItem.length;	
						var posicao = -1;
						for(i = 0; i <tamanho; i++){
							if(codigo==indice[i]){
								posicao=i;
								break;
							}
						}
						
						if(posicao==-1){
							totalItem.push(total);
							indice.push(codigo);
							
							// var tamanho = item.length;	
							
							tamanho = totalItem.length;	
							
							var totalcompra=0;
								
							for (i = 0; i <tamanho; i++) {	
									totalcompra=totalcompra+totalItem[i];
							} 
							
							var arredondado = parseFloat(totalcompra.toFixed(2));
							document.getElementById('total').innerHTML = arredondado;
							
							var newRow = $("<tr>");
						    var cols = "";
						    	
						    cols += '<td>'+codigo+'<input form="InserirVenda" value="'+codigo+','+quantidade+', '+total+'" style="display: none" type="checkbox" name="item" checked></td>';
						    cols += '<td>'+descricao+'</td>';
						    cols += '<td>'+quantidade+'</td>';
						    cols += '<td>'+total+'</td>';
						    cols += '<td>';
						    cols += '<button onclick="removerLinha(this)" type="button">Remover</button>';
						    cols += '</td>';
						    
						    cols += '</tr>';
						    
						    newRow.append(cols);
						    $("#produtos-tabela").append(newRow);
						    
						    contaLinhasTabela();
						}
						else{
							totalItem[posicao]=totalItem[posicao]+total;
							
							tamanho = totalItem.length;	
							
							var totalcompra=0;
							
							for (i = 0; i <tamanho; i++) {	
								totalcompra=totalcompra+totalItem[i];
							} 
							
							var arredondado = parseFloat(totalcompra.toFixed(2));
							document.getElementById('total').innerHTML = arredondado;
							
						}
					}
					
				    return false;
				  };
		 */
			