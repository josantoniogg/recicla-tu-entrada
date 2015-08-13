<?PHP

session_start();
session_destroy();
echo "<script language=Javascript> location.href=\"index.html\"; </script>"; 

?>