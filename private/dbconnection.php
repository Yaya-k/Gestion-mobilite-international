<?php
try // ici je me connecte a la base 
{ 
  $bdd = new pdo('mysql:host=localhost;port=3308;dbname=gestion_mobilite;charset=utf8', 'yaya', 'tse2020'); 

} catch(exception $e) 
{ 
  die('erreur : '.$e->getmessage()); 

}

