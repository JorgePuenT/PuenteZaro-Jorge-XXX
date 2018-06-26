Objetos creados (Añadidos al principio; relaciones en el objeto correspondiente):

-Admin2, su user account y sus carpetas
-examEntities:
	-examEntity1 - admin y newspaper4
	-examEntity2 - admin y newspaper2
	-examEntity3 - admin2 y newspaper2
	-examEntity4 - admin2 y newspaper5	(Fecha futura para que no se displayee)


----------------*CAMBIOS A REALIZAR Y COSAS A TENER EN CUENTA*--------------

-Acordarse de cambiar los langs que están como ****** por el correspondiente nombre de la entidad(no todos tienen el mismo numero de asteriscos pero tienen bastantes, recomiendo buscar 3 asteriscos en todo el proyecto)
-En messages de newspaper cambiar el atributo newspaper.examEntity.title por la clase indicada en ambos idiomas y los *******.
-Acordarse de revisar la query que te devuelve los examEntities pa un newspaper por si la restricción no es solo la fecha de publicación (el draft/final va implicito ya que si tiene newspaper tiene que estar en final por huevos)
-Palabras a cambiar(Search->File->replace...)[IMPORTANTE CASE SENSITIVE]:
	·examEntity
	·ExamEntity
	·examEntities
-Cambiar el ticker y ajustar a lo que pidan (está dentro del TODO)
-Revisar las anotaciones dependiendo de lo que pidan para cada campo (Pattern ticker)
-BEANS: Revisar los tickers para que se acoplen a lo que piden.


---------------*Orden de cosas*--------------------------------

1.Cambios en el UML
2.Cambios en el txt del Item4 (las queries)
3.Cambios en el proyecto
4.Rehacer el SQL dump
5.ReExportar el war
6.Rehacer el test de rendimiento y adjuntar el archivo


--------------*Comandos Necesarios (en orden)*----------------
!!!Cambiar *** por nombre del proyecto!!!

Ruta de SQL:
	cd C:\Program Files\MySQL\MySQL Server 5.5\bin
1.SqlDump(Entero, Para tests)
mysqldump -uroot -pV3rY=$tR0nG=P@$$w0rd$ AcmeNewspaper > "Z:\databaseAcmeNewspaper.sql"

1b.SqlDump(Vacio, purgado)
mysqldump -uroot -pV3rY=$tR0nG=P@$$w0rd$ AcmeNewspaper > "Z:\databaseAcmeNewspaperPurged.sql"

2.SqlDrop
mysql -uroot -pV3rY=$tR0nG=P@$$w0rd$ < "C:\Control\databaseDropScript.sql

3.SqlCreate
mysql -uroot -pV3rY=$tR0nG=P@$$w0rd$ < "C:\Control\databaseAcmeNewspaper.sql



PATTERNS:

[0][1-9]|[1-2][0-9]|[3][0-1]	DIA
[0][1-9]|[1][0-2]		MES
d{4}				AÑOS

^([0][1-9]|[1-2][0-9]|[3][0-1])([0][1-9]|[1][0-2])\d{4}$	INICIO+ DIA+MES+AÑOs +FINAL para limitar
/^([0][1-9]|[1-2][0-9]|[3][0-1])([0][1-9]|[1][0-2])\d{4}$/
>>>>>>> b48887b879d756bdf19321576dfa7e3cbfe2e75b
