Objetos creados (Añadidos al principio; relaciones en el objeto correspondiente):

-Admin2, su user account y sus carpetas
-examEntities:
	-examEntity1 - admin y newspaper4
	-examEntity2 - admin y newspaper2
	-examEntity3 - admin2 y newspaper2
	-examEntity4 - admin2 y newspaper5	(Fecha futura para que no se displayee)


----------------*CAMBIOS A REALIZAR Y COSAS A TENER EN CUENTA*--------------

-Acordarse de cambiar los langs que están como ****** por el correspondiente nombre de la entidad(no todos tienen el mismo numero de asteriscos pero tienen bastantes, recomiendo buscar 3 asteriscos en todo el proyecto)
-Acordarse de revisar la query que te devuelve los examEntities pa un newspaper por si la restricción no es solo la fecha de publicación (el draft/final va implicito ya que si tiene newspaper tiene que estar en final por huevos)
-Palabras a cambiar(Search->File->replace...)[IMPORTANTE CASE SENSITIVE]:
	·examEntity
	·ExamEntity
	·examEntities
-Cambiar el ticker y ajustar a lo que pidan (está dentro del TODO)
-Revisar las anotaciones dependiendo de lo que pidan para cada campo


---------------*Orden de cosas*--------------------------------

1.Cambios en el UML
2.Cambios en el proyecto
3.Rehacer el SQL dump
4.ReExportar el war
5.Rehacer el test de rendimiento y adjuntar el archivo
