db = db.getSiblingDB('mspedidos');

if (!db.getCollectionNames().includes('pedidos')) {
    db.createCollection('pedidos');
}