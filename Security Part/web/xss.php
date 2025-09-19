<?php
if (isset($_GET['q'])) {
    $q = $_GET['q'];
    echo "<h3>Search Result for: $q</h3>";
} else {
    echo "Use ?q=your_input";
}
?>
