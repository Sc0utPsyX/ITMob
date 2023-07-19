### Установка RabbitMQ:

- Установить erlang (https://www.erlang.org/downloads)
- Установить rabbitmq (https://www.rabbitmq.com/download.html)
- Через командную строку включить плагин для менеджмента
rabbitmq-plugins enable rabbitmq_management, 
запускать это надо из папки с rabbitmq/sbin
- После включения плагина станет доступен end-point: http://localhost:15672/
- Создать нового пользователя с правами админа:

rabbitmqctl add_user admin admin 

rabbitmqctl set_user_tags admin administrator

rabbitmqctl set_permissions -p / admin “.*” “.*” “.*”

