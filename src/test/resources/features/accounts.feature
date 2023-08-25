# language: ru

  Функция: Счета

    @Accounts @CreateJusanDeposit
    Сценарий: Открытие Депозитного счета "Jusan депозит"
      И Нажимаем на кнопку "Открыть депозит"
      И Выбираем тип депозита "Jusan депозит"
      И Выбираем валюту депозита "KZT" и подтверждаем
      И Выбираем валюту вклада "KZT"
      И Выбираем срок депозита "24 месяца" если он есть
      И Выбираем откуда переводить, например со счета "Текущий счет •8136"
      И Вводим случайную сумму вклада в "Jusan депозит" и подтверждаем


    @Accounts @CreateJusanSberPlus
    Сценарий: Открытие Депозитного счета "Jusan сберегательный+"
      И Нажимаем на кнопку "Открыть депозит"
      И Выбираем тип депозита "Jusan сберегательный+"
      И Выбираем валюту депозита "KZT" и подтверждаем
      И Выбираем валюту вклада "KZT"
      И Выбираем срок депозита "24 месяца" если он есть
      И Выбираем откуда переводить, например со счета "Текущий счет •8136"
      И Вводим случайную сумму вклада в "Jusan сберегательный+" и подтверждаем


    @Accounts @CreateAqylDeposit
    Сценарий: Открытие Депозитного счета "Депозит «AQYL»"
      И Нажимаем на кнопку "Открыть депозит"
      И Выбираем тип депозита "Депозит «AQYL»"
      И Выбираем валюту депозита "KZT" и подтверждаем
      И Выбираем валюту вклада "KZT"
      И Выбираем срок депозита "24 месяца" если он есть
      И Выбираем откуда переводить, например со счета "Текущий счет •8136"
      И Вводим случайную сумму вклада в "Депозит «AQYL»" и подтверждаем


    @Accounts @CheckingColvirStatus
    Сценарий: Проверка статуса в Colvir всех созданных депозитов
      И Проверяем статус депозита "Актуален" в Colvir