CREATE TABLE myUser (
    UUID VARCHAR(255) PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email LIKE '%@%.%'),
    hashpass VARCHAR(255) NOT NULL,
    type ENUM('freelancer', 'investor', 'entrepreneur') NOT NULL CHECK (type IN ('freelancer', 'investor', 'entrepreneur'))
);

CREATE TABLE Entrepreneur (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    domain VARCHAR(255),
    email_id VARCHAR(255),
    linkedin_link VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Education (
    UUID VARCHAR(255) PRIMARY KEY,
    institution VARCHAR(255),
    degree VARCHAR(255),
    major VARCHAR(255),
    year_of_completion VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Work_Experience (
    UUID VARCHAR(255) PRIMARY KEY,
    work_experience VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Company (
    UUID VARCHAR(255) PRIMARY KEY,
    company_name VARCHAR(255),
    is_registered VARCHAR(255),
    stakeholder VARCHAR(255),
    company_size VARCHAR(255),
    funding_status ENUM('Seed', 'Series A', 'Series B', 'Series C', 'Public'),
    equity_offered VARCHAR(255),
    assets VARCHAR(255),
    open_to_negotiations VARCHAR(255),
    profits_in_last_FY VARCHAR(255),
    pitch VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES Entrepreneur(UUID)
);

CREATE TABLE Freelancer (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    skills VARCHAR(255),
    email_id VARCHAR(255),
    linkedin_link VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Jobs (
    JOB_UUID VARCHAR(255) PRIMARY KEY,
    ENTREPRENEUR_UUID VARCHAR(255),
    description VARCHAR(255),
    is_active VARCHAR(255),
    number_of_openings VARCHAR(255),
    skills VARCHAR(255),
    pay_estimate VARCHAR(255),
    type ENUM('full-time', 'part-time', 'contract') CHECK (type IN ('full-time', 'part-time', 'contract')),
    posting_date DATE,
    FOREIGN KEY (ENTREPRENEUR_UUID) REFERENCES Company(UUID)
);

CREATE TABLE Investor (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    domain VARCHAR(255),
    funding_available VARCHAR(255),
    brands_built VARCHAR(255),
    email_id VARCHAR(255),
    linkedin_link VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);
