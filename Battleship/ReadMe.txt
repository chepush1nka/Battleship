Программа представляет собой реализацию игры "Морской бой", где игрок играет против флота компьютера, не имея своих кораблей.
Сразу при начале игры игроку предлагается сыграть либо в обычную игру, либо с модификациями. При выборе игры с модификациями
игроку будет предложено указать количество торпед, которые за один выстрел уничтожают вражеский корабль. Далее предлагается 
включить модификацию с восстановлением кораблей(не реализованно).

После этого пользователь вводит сначала ширину поля, затем его длину. На них наложено ограничение 9 < ширина/длина < 30,
так как если океан будет меньше, то можно расположить очень мало кораблей, а если больше, то океан не поместится на экран.

Затем пользователю выводится пустой океан и предлагается ввести 5 чисел через пробел - это количество кораблей каждого типа
на поле. Первое число - количество пятиклеточных ... последнее число - количество одноклеточных. 
Далее игрок произвдит выстрелы(два числа через пробел при обычной игре и префикс Т и два числа через пробел при игре с 
торпедами).

Состояние полей океана:
' ' - нетронутое поле
'◯' - промах
'X' - попадание
'█' - корабль уничтожен

 Игроку выводятся сообщения о результате его выстрела и при уничтожении всего флота противника выводится информация о
произведенных выстрелах и программа завершается.