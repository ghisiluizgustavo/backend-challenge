CREATE TABLE article (
    id int not null primary key,
    featured boolean not null,
    title varchar(255) not null,
    url varchar(255),
    image_url varchar(255),
    news_site varchar(255),
    summary varchar(525),
    published_at varchar(255),
    updated_at varchar(255)
);

CREATE TABLE event (
    id varchar(255) not null primary key,
    provider varchar(255),
    article_id int not null null,
    foreign key (article_id) references article (id)
);

CREATE TABLE launch (
    id varchar(255) not null primary key,
    provider varchar(255),
    article_id int not null null,
    foreign key (article_id) references article (id)
);

INSERT INTO article VALUES (
   13251,
   false,
   'MDA discusses status, mission objectives of Canada’s LEAP lunar rover',
   'https://www.nasaspaceflight.com/2021/12/leap-update-dec-2021/',
   'https://www.nasaspaceflight.com/wp-content/uploads/2020/12/lune-section-info-role-du-canada.jpg',
   'NASA Spaceflight"',
   'Humanity’s next steps in space exploration are focused on exploring like we never have before: returning to the Moon to establish a permanent, sustainable base as we move on to stepping foot on Mars — all of which the Canadian Space Agency (CSA) is actively participating in',
   '2021-12-10T21:16:28.000Z',
   '2021-12-10T21:20:12.741Z'
);

INSERT INTO article VALUES (
   13249,
   false,
   'Saving the shuttle simulator—“It was an artifact that needed to be preserved',
   'https://arstechnica.com/science/2021/12/saving-the-shuttle-simulator-it-was-an-artifact-that-needed-to-be-preserved/',
   'https://cdn.arstechnica.net/wp-content/uploads/2021/12/2021-1110E-Cockpit-Low-Angle.jpg',
   'Arstechnica',
   'We hope to have it in place at the museum in April.',
   '2021-12-10T14:57:36.000Z',
   '2021-12-10T15:10:16.997Z'
                           );

INSERT INTO article VALUES (
    13248,
    false,
    'Virgin Orbit adds Spire satellite to next launch',
    'https://spacenews.com/virgin-orbit-adds-spire-satellite-to-next-launch/',
    'https://spacenews.com/wp-content/uploads/2020/05/launcherone-inflight.jpg',
    'SpaceNews',
    'Virgin Orbit will fly a Spire cubesat in addition to several other payloads on its next LauncherOne launch, scheduled for no earlier than Dec. 22.',
    '2021-12-10T12:20:11.000Z',
    '2021-12-10T12:20:11.516Z'
);

INSERT INTO article VALUES (
    13247,
    false,
    'Rocket Report: Astra to launch from Florida, NASA troubleshoots SLS issue',
    'https://arstechnica.com/science/2021/12/rocket-report-astra-to-launch-from-florida-nasa-troubleshoots-sls-issue/',
    'https://cdn.arstechnica.net/wp-content/uploads/2021/12/GwtTgveA.jpeg',
    'Arstechnica',
    '\"Construction of Starship orbital launch pad at the Cape has begun.\"',
    '2021-12-10T12:00:01.000Z',
    '2021-12-10T12:37:53.128Z'
);


INSERT INTO launch VALUES ('65896761-b6ca-4df3-9699-e077a360c52a','Launch Library 2', 13247);
INSERT INTO launch VALUES ('2b74e95d-de9d-44e5-aefb-d1e48d9e35e6','Launch Library 2', 13247);