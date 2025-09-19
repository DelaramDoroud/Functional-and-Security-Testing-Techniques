FROM python:3.9-slim
WORKDIR /app
COPY . .
RUN pip install requests
ENV ATTACK_TYPE=1
CMD ["python", "main.py"]