CREATE TABLE movies(
    movie_id IDENTITY PRIMARY KEY,
    title TEXT NOT NULL
);

CREATE TABLE events(
    event_id IDENTITY PRIMARY KEY,
    content TEXT NOT NULL,
    sent BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL
)
