FROM php:8.0-apache
RUN docker-php-ext-install mysqli
COPY index.php /var/www/html/
COPY simpledata.sql /var/www/html/
COPY xss.php /var/www/html/