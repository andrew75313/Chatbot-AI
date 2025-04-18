DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'chatbotai') THEN
        CREATE DATABASE chatbotai;
END IF;
END $$;